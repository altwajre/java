/**
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package c6;

import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.Guice;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.Enumeration;

@RequestScoped
public class HelloServlet extends HttpServlet {
	public void init(ServletConfig config) {
  }


  public static void main(String[] args) throws ServletException {
    Guice.createInjector(new ServletModule() {
        protected void configureServlets() {

          serve("/hello").with(HelloServletAdapter.class);
        }
    });

    new GuiceFilter().init(new FilterConfig() {
      public String getFilterName() {
        return "foo";
      }

      public ServletContext getServletContext() {
        return null;
      }

      public String getInitParameter(String s) {
        return s;
      }

      public Enumeration getInitParameterNames() {
        return null;
      }
    });
    
  }
}
