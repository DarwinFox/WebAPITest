package com.example.WebAPITest

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.ktor.auth.UserPasswordCredential
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.UUID.randomUUID

data class MyJsonData(var UsersIdString: String) {

    @SerializedName("userid") var userId: String = "Temp"
    @SerializedName("userfirstname") var userFirstName: String = "NoName"
    @SerializedName("userlastname") lateinit var userLastName: String
    @SerializedName("userdob")lateinit var userDOB: String
//    @SerializedName("userdob")lateinit var userDOB: LocalDateTime
//    lateinit var userPasswordCredential: UserPasswordCredential

    //todo define internal variables
//    var internalId = randomUUID()
//    var userCreationDateTime: LocalDateTime = LocalDateTime.now()


//    fun checkId(): Boolean{
//        if (userId == UsersIdString) {
//        return true
//        }
//        else{
//            return false
//        }
//    }

//    fun setUserId(){
//       if(userId == "Temp")
//        userId = UsersIdString
//        userCreationDateTime = LocalDateTime.now()
//    }
//
//    fun getUserInternalId(): UUID? {
//        return internalId
//    }

    /* todo determine which specific data should be returned rather than all data in the entire class */
//    fun getUserDataJsonString(): String {
//
//        return ("{\"MyJsonData\" : " +
//                "[{\"userid\" : " + "\"" + this.userId + "\"}, " +
//                "{\"userfirstname\" : " + "\"" + this.userFirstName + "\"}, " +
//                "{\"userlastname\" : " + "\"" + this.userLastName + "\"}, " +
//                "{\"userdob\" : " + "\"" + this.userDOB.format(DateTimeFormatter.ofPattern("M/d/y")) + "\"}" +
//                "]}")
//    }

}