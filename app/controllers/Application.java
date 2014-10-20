package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import static play.data.Form.*;


import views.html.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application extends Controller {

    /*
    //Local storage lists
    */
    private static List<Customer> customersList = new ArrayList<>();
    private static List<Integer> localIdsList = new ArrayList<>();


    @Security.Authenticated(Secured.class)
    public static Result index() {
        return GO_HOME;

    }


    public static class Login {

        public String email;
        public String password;



        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }




    @Security.Authenticated(Secured.class)
        public static Result list() {

        if (localIdsList == null || localIdsList.isEmpty()) {

          localIdsList.addAll(getIds());
        }
            return ok(
                    list.render(localIdsList)
            );
        }


    public static Result refreshIds() {
        localIdsList.clear();
        customersList.clear();
        return GO_HOME;
    }

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
            routes.Application.list()
    );


    /**
     * Display the 'edit form' of a existing customer.
     *
     * @param id Id of the customer to edit
     */
    public static Result edit(Integer id) {

        Customer customer = returnLocalCustomer(customersList, id);

        Date date = asDate(customer.getBornDate());
        String surname = customer.getSurname().getValue();

        return ok(
                editForm.render(id, surname, date)
        );
    }

    
    public static List<Customer> addCustomerToLocalList(Customer customer) {
        ArrayList<Customer> localList = new ArrayList<>();
        localList.add(customer);
        return localList;
    }

    
    public static Customer returnLocalCustomer(List<Customer> customerList, Integer id) {
            Customer customer = null;
        if (!(customerList == null || customerList.isEmpty())) {
            for (Customer i : customerList) {
                Integer localId = i.getID();
                if (localId == id) {
                    customer = i;
                }
            }
        } else {
            customer = findById(id);
            addCustomerToLocalList(customer);
        }
        return customer;
    }


    /**
     * Handle the 'edit form' submission
     *
     * @param id Id of the customer to edit
     */
    public static Result update(Integer id) throws ParseException {
        DynamicForm requestData = Form.form().bindFromRequest();
        String surname = requestData.get("surname");
        String dateStr = requestData.get("data");

        if (requestData.hasErrors()) {
            return badRequest(editForm.render(id, surname, toDate(dateStr)));
        }
        returnLocalCustomer(customersList, id).update(requestData);
        flash("success", "Customer " + requestData.get("surname") + " has been updated");
        return GO_HOME;
    }


    public static Date toDate(String dateStr){

        Date date = null;

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DDThh:mm:ssTZD");

        try {

            date = formatter.parse(dateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * Display the 'new form'.
     */
    public static Result create() {
        Form<Customer> customerForm = form(Customer.class);
        return ok(
               createForm.render(customerForm)
        );
    }


    /**
     * Handle the 'new form' submission
     */
    public static Result save() {
        Form<Customer> customerForm = form(Customer.class).bindFromRequest();
        if (customerForm.hasErrors()) {
            return badRequest(createForm.render(customerForm));
        }
        Customer customer = customerForm.get();
        customersList.add(customer);
        flash("success", "Customer " + customerForm.get().getID() + " has been created");
        return GO_HOME;
    }


    /**
     * Handle customer deletion
     */
    public static Result delete(Integer id) {

                localIdsList.remove(id);
            customersList.remove(id);

        flash("success", "Customer has been deleted");
        return GO_HOME;
    }





// DatatypeFactory creates new javax.xml.datatype Objects that map XML
// to/from Java Objects.
    private static DatatypeFactory df = null;

    static {
        try {
            df = DatatypeFactory.newInstance();
        } catch(DatatypeConfigurationException e) {
            throw new IllegalStateException(
                    "Error while trying to obtain a new instance of DatatypeFactory", e);
        }
    }

// Converts a java.util.Date into an instance of XMLGregorianCalendar
    public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
        if(date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            return df.newXMLGregorianCalendar(gc);
        }
    }

// Converts an XMLGregorianCalendar to an instance of java.util.Date
    public static java.util.Date asDate(XMLGregorianCalendar xmlGC) {
        if (xmlGC == null) {
            return null;
        } else {
            return xmlGC.toGregorianCalendar().getTime();
        }
    }


    public static Result login() {

        return ok(
                login.render(Form.form(Login.class))
        );
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Application.login()
        );
    }


    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }


    public static Customer findById(Integer id) {
        TaskService taskService = new TaskService();
        ITaskService iTaskService = taskService.getBasicHttpBindingITaskService();
        Customer customer = iTaskService.getCustomerData(id);
        return customer;
    }


    public static List<Integer> getIds() {
        TaskService taskService = new TaskService();
        ITaskService iTaskService = taskService.getBasicHttpBindingITaskService();
        ArrayOfint customersIds = iTaskService.getCustomersIds();
        return customersIds.getInt();
    }


}

