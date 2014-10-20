package controllers;


import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

/**
 * Created by rafa on 16.10.14.
 */


    public class Secured extends Security.Authenticator {

        @Override
        public String getUsername(Context ctx) {
            return ctx.session().get("email");
        }

        @Override
        public Result onUnauthorized(Context ctx) {
            return redirect(routes.Application.login());
        }
    }

