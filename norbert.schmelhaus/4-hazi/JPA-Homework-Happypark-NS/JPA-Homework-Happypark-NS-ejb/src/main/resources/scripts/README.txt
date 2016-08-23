##################################################################################
##################################################################################

			README.txt for JPA-Homework-Happypark-NS project
			
				Created by: Schmelhuas Norbert
			
				e-mail: sch.norbeee@gmail.com

##################################################################################

Resource URI: http://localhost:8080/JPA-Homework-Happypark-NS-web/rest


INFO: 	I use every REST endpoints application/json header. I tested every endpoints with .sh scripts in GIT-BASH.
		Every script is in project folder:
		
				JPA-Homework-Happypark-NS\JPA-Homework-Happypark-NS-ejb\src\main\resources\scripts\

				
Resource descriptions:
	
1.	AddressResource.java	@Path("address")

	1.1. Add new address to database:
	
		@POST
		@Path("add")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/address/add
		JSON Body:
		{
			"zipCode": "8100",
			"country": "HUN",
			"city": "Dorog",
			"street": "Hosszu utca",
			"houseNumber": "5/11"
		}
		Tested by: scripts\address_add_new_address4.sh		with json like body.: scripts\newaddress.json
	
	1.2. Read one address by ID:
		
		@GET
		@Path("search")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/address/search?address_pk=1
		Tested by: scripts\address_search_address1.sh
		
	1.3. Update a valid address by ID:
	
		@PUT
		@Path("update")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/address/update?addres_pk=4
		JSON Body:
		{
			"id": "4",
			"zipCode": "8111",
			"country": "HUN",
			"city": "Dorog",
			"street": "Hosszu utca",
			"houseNumber": "5/8"
		}
		Tested by: scripts\address_update_address4.sh		with json like body.: scripts\oldaddress.json
		
	1.4. Delete one address in to database by ID:
	
		@DELETE
		@Path("delete")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/address/delete?address_pk=4
		Tested by: scripts\address_delete_address4.sh
		

2.	GuestBookResource.java	@Path("book")
	
	2.1. Add new description to guestbook by guest ID and park ID:
	
		@POST
		@Path("add")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/book/add?guest_pk=1&park_pk=1
		JSON Body:
		"Van valami van valami..."
		Tested by: scripts\book_add_description_guest1_park1.sh		with json like body.: scripts\desc.json
	
	2.2. Search description by guest ID and park ID:
	
		@GET
		@Path("get")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/book/get?guest_pk=1&park_pk=1
		Tested by: scripts\book_get_guest1_park1.sh
		
		
3. 	GuestResource.java	@Path("guests")

	3.1. Add new guest to database:
	
		@POST
		@Path("add")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/add
		JSON Body:
		{
			"money": 30000,
			"registration": "2016-08-23",
			"age": 39
		}
		Tested by: scripts\guests_add_new_guest4.sh		with json like body.: scripts\newguest.json
	
	3.2. Read one guest by ID:
		
		@GET
		@Path("search")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/search?guest_pk=1
		Tested by: scripts\guests_search_guest1.sh
		
	3.3. Update a valid guest by ID:
	
		@PUT
		@Path("update")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/update?guest_pk=4
		JSON Body:
		{
			"id": 4,
			"money": 50000,
			"registration": "2016-08-23",
			"age": 39
		}
		Tested by: scripts\guests_update_guest4.sh		with json like body.: scripts\oldguest.json
		
	3.4. Delete one guest in to database by ID:
	
		@DELETE
		@Path("delete")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/delete?guest_pk=4
		Tested by: scripts\guests_delete_guest4.sh
		
	3.5. Add guest to park by guest ID and park ID:

		@POST
		@Path("add_to_park")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/add_to_park?guest_pk=1&park_pk=1
		Tested by: scripts\guests_add_guest1_to_park1.sh
		
	3.6. Remove guest to park by guest ID and park ID:
	
		@DELETE
		@Path("remove_to_park")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/remove_to_park?guest_pk=1&park_pk=1
		Tested by: scripts\guests_remove_guest1_to_park1.sh
		
	3.7. Add guest to machine by guest ID, park ID and machine ID:
	
		@POST
		@Path("add_to_machine")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/add_to_machine?guest_pk=1&park_pk=1&machine_pk=3
		Tested by: scripts\guests_add_guest1_to_machine3.sh
		
	3.8. Remove guest to machine by guest ID and machine ID:
	
		@DELETE
		@Path("remove_to_machine")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/remove_to_machine?guest_pk=1&machine_pk=3
		Tested by: scripts\guests_remove_guest1_to_machine3.sh
		
		
4.	HappiparkResource.java	@Path("parks")

	4.1. Add new park to database:
	
		@POST
		@Path("add")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/add
		JSON Body:
		{
			"name": "ParkNew",
			"address": {
				"id": 3,
				"zipCode": "5100",
				"country": "HUN",
				"city": "Pecs",
				"street": "Nagy utca",
				"houseNumber": "1/11"
			},
			"capital": 25000000,
			"totalArea": 35000,
			"ticketPrice": 1200
		}
		Tested by: scripts\parks_add_new_park3.sh		with json like body.: scripts\newparkwithaddress.json
	
	4.2. Read one park by ID:
		
		@GET
		@Path("search")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/search?park_pk=1
		Tested by: scripts\parks_search_park1.sh
		
	4.3. Update a valid park by ID:
	
		@PUT
		@Path("update")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/update?park_pk=3
		JSON Body:
		{
			"id": 3,
			"name": "ParkNew",
			"address": {
				"id": 3,
				"zipCode": "5100",
				"country": "HUN",
				"city": "Pecs",
				"street": "Nagy utca",
				"houseNumber": "1/11"
			},
			"capital": 5000000,
			"totalArea": 35000,
			"ticketPrice": 1200
		}
		Tested by: scripts\parks_update_park3.sh		with json like body.: scripts\oldparkwithaddress.json
		
	4.4. Delete one park in to database by ID:
	
		@DELETE
		@Path("delete")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/delete?park_pk=3
		Tested by: scripts\parks_delete_park3.sh
		
	4.5. Add machine to park by park ID and machine ID:
	
		@POST
		@Path("add_machine")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/add_machine?park_pk=1&machine_pk=1
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/add_machine?park_pk=2&machine_pk=2
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/add_machine?park_pk=1&machine_pk=3
		Tested by: scripts\parks_add_machine1_to_park1.sh
					scripts\parks_add_machine2_to_park2.sh
					scripts\parks_add_machine3_to_park1.sh
					
	4.6. Remove machine to park by park ID and machine ID:
	
		@DELETE
		@Path("remove_machine")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/remove_machine?park_pk=1&machine_pk=1
		Tested by: scripts\parks_remove_machine1_park1.sh
		
	4.7. List machines to one park by park ID:
	
		@GET
		@Path("machines")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/machines?park_pk=1
		Tested by: scripts\parks_machines_park1.sh
		
	4.8. List active guest who don't use machine by park ID:
	
		@GET
		@Path("unused_active_guests")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/unused_active_guests?park_pk=1
		Tested by: scripts\parks_unused_active_guests_park1.sh
		
	4.9. Count active guest who don't use machine by park ID:
	
		@GET
		@Path("unused_active_guests_count")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/parks/unused_active_guests_count?park_pk=1
		Tested by: scripts\parks_unused_active_guests_count_park1.sh
		
		
5. 	MachineResource.java	@Path("machines")

	5.1. Add new machine to database:
	
		@POST
		@Path("add")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/add
		JSON Body:
		{
			"area": 300,
			"ticketPrice": 2000,
			"seats": 40,
			"freeSeats": 40,
			"type": "ROLLERCOASTER",
			"priceOfMachine": 700000
		}
		Tested by: scripts\machines_add_new_machine4.sh		with json like body.: scripts\newmachine.json
	
	5.2. Read one machine by ID:
		
		@GET
		@Path("search")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/search?machine_pk=1
		Tested by: scripts\machines_search_machine1.sh
		
	5.3. Update a valid machine by ID:
	
		@PUT
		@Path("update")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/update?machine_pk=4
		JSON Body:
		{
			"id": 4,
			"area": 300,
			"ticketPrice": 2000,
			"seats": 39,
			"freeSeats": 39,
			"type": "ROLLERCOASTER",
			"priceOfMachine": 700000,
			"ageLimit": 21
		}
		Tested by: scripts\machines_update_machine4.sh		with json like body.: scripts\oldmachine.json
		
	5.4. Delete one machine in to database by ID:
	
		@DELETE
		@Path("delete")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/delete?machine_pk=4
		Tested by: scripts\machines_delete_machine4.sh

	5.5. What park use machine by machine ID:
	
		@GET
		@Path("park_use")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/park_use?machine_pk=1
		Tested by: scripts\machines_park_use_machine1.sh
		
	5.6. List guest who use machine by machine ID:
	
		@GET
		@Path("guest_use")
		http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/machines/guest_use?machine_pk=3
		Tested by: scripts\machines_guest_use_machine3.sh
		