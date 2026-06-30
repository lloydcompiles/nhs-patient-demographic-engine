# NHS Patient Demographic Engine

A console-based Java application that lets health professionals register, search for, and display patient records. 

## About
This app was built as a hands-on Java learning project. It demonstrates core Java concepts including: custom exceptions, enums, ArrayList, Scanner-based CLI.

## NHS Context
Patient data in NHS systems is structured around the FHIR R4 standard. This project maps its domain model directly to the FHIR R4 Patient resource.

## Project Status
🚧 In development

## Features (once complete)
User will see a main menu with three options: 
 1. Register patient
 2. Search patient
 3. Exit 
After registering or viewing a result, they get Y/N prompts to continue or go back to main menu 

### The Patient details will have the following information:
- NHS Number (validated to be 10 digits)
- Last Name, First Name and Prefix (e.g. Mr/Mrs)
- Contact Details i.e. phone/mobile number and email address
- Gender i.e. male, female or other (defaulted to ‘unknown’ if not provided) 

## Future Ideas
- Add format validation for phone numbers and email addresses
- GUI Interface

## Built With
- Java
- IntelliJ IDEA

## How to Run
1. Clone the repo: 'git clone https://github.com/lloydcompiles/nhs-patient-demographic-engine.git'
2. Open the project in IntelliJ IDEA
3. Run 'Main.java'

## Author
lloydcompiles
