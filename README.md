# test_repo
test repo for fetching the details lead
here two RestApi created for creating and fetching the leads details from db
 db used is postgresql.


//for creating the lead data
url:-  http://localhost:8080/api/leads/createLead

ReqBody:
{
    "leadId": "5678",
    "firstName": "Vineet",
    "middleName": "F",
    "lastName": "KV",
    "mobileNumber": "8877887788",
    "gender": "Male",
    "dob": "2024-01-23",
  "email": "v@gmail.com"
}

Response :
after the adding the lead details in db 
{
    "status": "E10010",
    "messages": [
        "Lead Already Exists in the database with the lead id"
    ]
}

// for fetching the data from db
 url:- http://localhost:8080/api/leads/mobile/8877887788

 response :
 {
    "status": "success",
    "data": "[Lead(leadId=5678, firstName=Vineet, middleName=F, lastName=KV, mobileNumber=8877887788, gender=Male, dob=2024-01-23, email=v@gmail.com)]"
}
 
