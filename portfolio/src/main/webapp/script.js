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

/**
 * Adds a random greeting to the page.
 */
function addRandomQuote() {
  const greetings =
      ['Good Grief - Jotaro Kujo', 'I am Batman - Batman (Obviously)', 'Oh Jeez Rick - Morty', 'Like Zoinks Man - Shaggy'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('quote-container');
  greetingContainer.innerText = greeting;
}


function getFromDataUsingArrow() {
  var i;
  fetch('/data').then(response => response.json()).then((data) => {
    const dataListElement = document.getElementById('data-container');
    dataListElement.innerHTML = '';
    for (i=0; i<data.length; i++) {
      dataListElement.appendChild(
        createListElement('Message ' + (i+1) + ': ' + data[i]));
    }
  });
}

function getSelectAmountComments() {
  var i;
  fetch('/data').then(response => response.json()).then((data) => {
    do {
      var commentsShown = prompt("Enter a  number (1-10)");
      commentsShown = parseInt(commentsShown,10);
    } while (commentsShown < 1 || commentsShown > 10);
    console.log("Inputted", commentsShown);
    const commentElement = document.getElementById('comment-container');
    commentElement.innerHTML = '';
    if (data.length < commentsShown) {
      for (i=0; i<data.length; i++) {
        commentElement.appendChild(
        createListElement('Message ' + (i+1) + ': ' + data[i]));
      }
    } else {
      for (i=0; i<commentsShown; i++) {
        commentElement.appendChild(
        createListElement('Message ' + (i+1) + ': ' + data[i]));
      }
    }
  });
}

async function deleteAllComments() {
  await fetch("/delete-data", {
    method: "POST", 
      
    body: JSON.stringify({ 
      title: "foo", 
      body: "bar", 
      userId: 1 
    }), 
        
    headers: { 
      "Content-type": "application/json; charset=UTF-8"
    } 
  });

  await refreshComments();
}

function refreshComments() {
  const commentNode = document.getElementById("comment-container");
  const dataNode = document.getElementById("data-container");
  while(commentNode.firstChild) {
    commentNode.removeChild(commentNode.firstChild);
  }
  
  while (dataNode.firstChild) {
    dataNode.removeChild(dataNode.firstChild);
  }
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}