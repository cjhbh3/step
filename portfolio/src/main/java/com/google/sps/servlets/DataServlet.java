// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  ArrayList<String> messages = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Convert messages to JSON
    String json = convertToJson(messages);

    // Send JSON to /data
    response.setContentType("text/html;");
    response.getWriter().println(json);
  }

  @Override 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Receive info from form
    // String firstName = getParameter(request,"fname","");
    // String lastName = getParameter(request,"lname","");
    String information = getParameter(request,"info","");
    boolean upperCase = Boolean.parseBoolean(getParameter(request,"upper-case","false"));
    boolean sort = Boolean.parseBoolean(getParameter(request,"sort","false"));

    // Apply Settings
    if (upperCase) {
      information = information.toUpperCase();
    }

    // Break up comma split list into words
    String[] words = information.split("\\s*, \\s*");

    if (sort) {
      Arrays.sort(words);
    }

    // Add words to our message ArrayList
    for (String word : words) {
      messages.add(word);
    }

    // Send words to /data and redirect user back to index.html
    response.setContentType("text/html;");
    response.getWriter().println(Arrays.toString(words));
    response.sendRedirect("/index.html");
  }

  private String convertToJson(ArrayList<String> array) {
    // Changed to utilizing Gson to make taking user input easier
    Gson gson = new Gson();
    String json = gson.toJson(array);
    return json;
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
