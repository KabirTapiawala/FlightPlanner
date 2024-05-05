Flight Management System

Project Overview:
This Flight Management System calculates optimal travel routes between major cities like Chicago, Dallas, Austin, and Houston based on cost or time preferences. It processes flight data and user requests from text files, outputting the most efficient routes.

Features:
- Reads and processes input from text files.
- Outputs the top three travel paths based on cost or time efficiency.
- Utilizes Java's LinkedList, Stack, and Arrays to manage data effectively.

Getting Started:

Prerequisites:
- Java JDK 11 or higher is required to run the program.
- Git, if you wish to clone the repository.

Installation:
1. Clone the repository:
   $ git clone https://github.com/KabirTapiawala/flight-management-system.git
2. Navigate to the project directory:
   $ cd flight-management-system

Setup:
Ensure that the 'FlightData.txt' and 'RequestedFlightFile.txt' files are correctly formatted and placed in the project directory.

Running the Program:
Execute the program using Java:
$ java FlightManager
This will read the input files, process the requests, and print the results based on the specified criteria in the request file.

Input File Formats:

FlightData.txt
Format: Origin|Destination|Cost|Duration
Example entries:
Chicago|Dallas|220|95
Dallas|Chicago|215|90

RequestedFlightFile.txt
Format: Origin|Destination|C/T (C for cost, T for time)
Example entries:
Chicago|Dallas|C
Austin|Houston|T

Contributing:
Contributions are welcome. To contribute:
- Fork the repository.
- Create a feature branch: 
  $ git checkout -b new-feature
- Commit your changes: 
  $ git commit -am 'Add some feature'
- Push to the branch: 
  $ git push origin new-feature
- Submit a pull request.

License:
This project is inspired by Professor Khan

Acknowledgments:
- Thanks to Professor Khan for project guidance.
- All contributors who test and improve this system.
