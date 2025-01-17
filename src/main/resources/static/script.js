// set listener on form
const geoForm = document.getElementById("geo-form");
geoForm.addEventListener("submit", handleFormSubmission);

// invoke service, loading data in database
window.onload = retrieveAllEntries();

//  override form submission behavior of browser
function handleFormSubmission(event) {
    event.preventDefault();
    let address = document.getElementById("address").value;
    processFormPosting(address);
}

function processFormPosting(address) {
    // POST new address
    fetch('/geocode/address', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json' // Set the content type to JSON
        },
        body: JSON.stringify({ 'address': address })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            // serialize json to object
            return response.json();
        })
        .then(data => {
            // process data from response
            addRowToTable(data);
        })
        .catch(error => {
            // error handling
            window.alert('There has been a problem with your address submission:');
            console.error('There has been a problem with your address submission:', error);
        });

}


// Function to add a new row to the table 
function addRowToTable(data) {
    
    // obtain table element
    let table = document.getElementById("addressTable");

    // insert row and cells into DOM
    let newRow = table.insertRow();
    let cell1 = newRow.insertCell(0);
    let cell2 = newRow.insertCell(1);
    let cell3 = newRow.insertCell(2);

    // set the data
    cell1.innerHTML = data.formattedAddress;
    cell2.innerHTML = data.latitude;
    cell3.innerHTML = data.longitude;

}

// Retrieve all entries from server/database
function retrieveAllEntries() {

    // Retrieve all entries as list
    fetch('/geocode/retrieve', {
        method: "GET",
        headers: {
            'Accept': 'application/json' // Set the content type to JSON
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            // serialize json to object
            return response.json();
        })
        .then(data => {
            // retrieve all and iterate appending to table
            if (data)
                data.forEach(dataElement => {
                    addRowToTable(dataElement);
                });
        })
        .catch(error => {
            // error handling
            let errMsg = 'Problem while retrieving saved entries.';
            window.alert(errMsg);
            console.error(errMsg, error);
        });
}