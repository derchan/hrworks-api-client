package com.aoe.hrworks

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.Date

interface HrWorksClient {

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetPresentPersonsOfOrganizationUnit")
    fun getPresentPersonsOfOrganizationUnit(@Body request: GetPresentPersonsOfOrganizationUnitRq): Single<PersonList>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAllOrganizationUnits")
    fun getAllOrganizationUnits(): Single<OrganizationUnitList>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAllActivePersons")
    fun getAllActivePersons(@Body request: GetAllActivePersonsRq): Single<Map<String, List<Person>>>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAllActivePersons")
    fun getAllActivePersons(): Single<Map<String, List<Person>>>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAvailableWorkingHours")
    fun getAvailableWorkingHours(@Body request: GetAvailableWorkingHoursRq): Single<Map<String, List<Availability>>>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAllAbsenceTypes")
    fun getAllAbsenceTypes(): Single<AbsenceTypeList>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAllAbsenceTypes")
    fun getAllAbsenceTypes(@Body request: GetAllAbsenceTypesRq): Single<AbsenceTypeList>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetLeaveAccountData")
    fun getLeaveAccountData(@Body request: GetLeaveAccountDataRq): Single<Map<String, LeaveAccountData>>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAbsences")
    fun getAbsences(@Body request: GetAbsencesRq): Single<Map<String, List<AbsenceData>>>

    @POST("/")
    @Headers("${HrWorksClientBuilder.HEADER_HR_WORKS_TARGET}: GetAbsences")
    fun getAccumulatedAbsences(@Body request: GetAccumulatedAbsencesRq): Single<Map<String, List<AccumulatedAbsenceData>>>
}

data class PersonList(
    val persons: List<Person>
)

data class OrganizationUnitList(
    val organizationUnits: List<OrganizationUnit>
)

data class AbsenceTypeList(
    val absenceTypes: List<AbsenceType>
)

data class OrganizationUnit(
    val organizationUnitNumber: String,
    val organizationUnitName: String
)

data class AbsenceData(
    val beginDate: Date,
    val endDate: Date,
    val absences: List<Absence>
)

data class AccumulatedAbsenceData(
    val beginDate: Date,
    val endDate: Date,
    val absences: Map<String, Double>
)

data class Availability(
    val beginDate: Date,
    val endDate: Date,
    val workingHours: Double
)

data class LeaveAccountData(
    val holidayEntitlement: Int,
    val requested: Int,
    val approved: Int,
    val unplanned: Int,
    val planned: Int
)

data class Person(
    val personnelNumber: String,
    val personId: String,
    val firstName: String,
    val lastName: String
)

data class AbsenceType(
    val name: String,
    val key: String,
    val type: String,
    val isActive: Boolean,
    val reducesHolidayEntitlement: Boolean
)

data class Absence(
    val name: String,
    val absenceTypeKey: String,
    val beginDate: Date,
    val endDate: Date,
    val status: String,
    val workingDays: String
)

data class GetPresentPersonsOfOrganizationUnitRq(
    val organizationUnitNumber: String
)

data class GetAllActivePersonsRq(
    val organizationUnits: List<String>
)

data class GetAvailableWorkingHoursRq(
    val beginDate: Date,
    val endDate: Date,
    @SerializedName("persons")
    val idOrPersonnelNumberList: List<String>,
    val interval: IntervalType? = null,
    val usePersonnelNumbers: Boolean = false
)

data class GetAllAbsenceTypesRq(
    val onlyActive: Boolean = true
)

data class GetLeaveAccountDataRq(
    val referenceDate: Date = Date(),
    @SerializedName("persons") val idOrNumberList: List<String>,
    val usePersonnelNumbers: Boolean = false
)

data class GetAbsencesRq(
    val beginDate: Date,
    val endDate: Date,
    @SerializedName("persons")
    val idOrPersonnelNumberList: List<String>,
    val interval: IntervalType? = null,
    val usePersonnelNumbers: Boolean = false
) {
    val count = false
}

data class GetAccumulatedAbsencesRq(
    val beginDate: Date,
    val endDate: Date,
    @SerializedName("persons")
    val idOrPersonnelNumberList: List<String>,
    val interval: IntervalType? = null,
    val usePersonnelNumbers: Boolean = false
) {
    val count = true
}
