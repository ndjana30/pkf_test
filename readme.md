Create empty postgresql database with dbname: postgres, username: postgres, password:postgres 

Run project
Original(Basic) link is: http://localhost:8080/pkf/rdv. let's call it origin
To create a client, link is origin/client/create. request params are email: String, telephone: Integer, name: String, Surname: String

To create a responsible, link is origin/responsible/create. the params are thesame as to create a client 

To create a service, origin/service/create. the request params are name: String 

To assign a responsible to a service, the link is origin/service/{service_id}/{responsible_id}. The path variables are service_id: Long, and responsible_id: Long

To create a rendezvous, the link is origin/create/{service_id}/{responsible_id}/{client_id}. The path variables are all of type Long, and the request params are date: Date(dd-MM-yyyy), motif: String, time: LocalTime(HH:mm:ss).
