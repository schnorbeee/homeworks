curl --verbose --write-out "\n" --request DELETE --header "Content-Type: application/json" --cookie-jar cart-cookies.txt --cookie cart-cookies.txt "http://localhost:8080/JPA-Homework-Happypark-NS-web/rest/guests/remove_to_park?guest_pk=1&park_pk=1"