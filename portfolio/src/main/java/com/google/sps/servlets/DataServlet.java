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

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //Create an ArrayList of strings and add three messages
    ArrayList<String> messages = new ArrayList<String>();
    messages.add("Hello Friends");
    messages.add("Howdy Partner");
    messages.add("Heyo Bucko");

    // Convert messages to JSON
    String json = convertToJson(messages);

    // Send JSON to /data
    response.setContentType("text/html;");
    response.getWriter().println(json);
  }

  private String convertToJson(ArrayList<String> array) {
    String json = "{";
    json += "\"Message1\": ";
    json += "\"" + array.get(0) + "\"";
    json += ", ";
    json += "\"Message2\": ";
    json += "\"" + array.get(1) + "\"";
    json += ", ";
    json += "\"Message3\": ";
    json += "\"" + array.get(2) + "\"";
    json += "}";
    return json;
  }
}
