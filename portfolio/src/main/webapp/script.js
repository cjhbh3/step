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
  fetch('/data').then(response => response.text()).then((quote) => {
    document.getElementById('data-container').innerText = quote;
  });
}