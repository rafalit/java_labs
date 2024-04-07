# Basket Splitter
Our online store is getting bigger with new items like electronics and household goods. Some of these items are too big for our usual delivery trucks, and others come from different suppliers needing special delivery services. To make things run smoothly,</br > we need to organize what customers buy based on how they can be delivered. The idea is to group items together for delivery in a way that we make as few trips as possible. We'll use a set system and a file that tells us how each product can be delivered.</br>Your job is to create a tool that does this grouping automatically.
## Features 
* **Configurable Delivery Options**: Define various delivery options for each product in the configuration file.
* **Dynamic Splitting**: Automatically group products into delivery options based on predefined configurations.
* **Error Handling**: Handle exceptions gracefully, providing informative error messages for easy debugging.
* **Unit Testing**: Comprehensive unit tests ensure the reliability and correctness of the application.
## Usage
1) **Configure Delivery Options**: Update the config.json file located in the src/main/resources directory. Define delivery options for each product according to your requirements.
2) **Prepare Basket Files**: Place your basket files (in JSON format) in the src/main/resources directory. Each basket file should contain a list of products.
3) **Run the Application**: Execute the App.java file to split the baskets into delivery groups. The application will display the delivery groups for each basket in the console.
## Example
````
["Cocoa Butter": ["Next day shipping", "Mailbox delivery", "In-store pick-up", "Parcel locker", "Courier", "Same day delivery"],
  "Tart - Raisin And Pecan": ["Express Collection", "Courier", "Mailbox delivery"],
  "Table Cloth 54x72 White": ["Next day shipping", "Parcel locker", "Mailbox delivery", "Courier"],
  "Flower - Daisies": ["Courier", "Pick-up point", "In-store pick-up", "Express Collection", "Same day delivery"],
  "Fond - Chocolate": ["Pick-up point", "Express Collection", "Mailbox delivery"],
  "Cookies - Englishbay Wht": ["Courier", "Pick-up point", "Express Collection", "Parcel locker"]]

After running the BasketSplitter, the baskets will look like this:

Pick-up point: [Fond - Chocolate]
Courier: [Cocoa Butter, Tart - Raisin And Pecan, Table Cloth 54x72 White, Flower - Daisies, Cookies - Englishbay Wht]
````
## Testing
The project includes comprehensive unit tests to ensure the reliability and correctness of the application. You can run the tests using JUnit.
## Authors
Rafał Szarowicz AGH of Krakow
