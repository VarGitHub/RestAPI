package apitests;

import apiclients.AllEmployeesData;
import apiclients.Employees;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestAllEmployeesData {

    AllEmployeesData allEmployeesData;
    Employees emp = new Employees();
    List<Employees> empList = new ArrayList<>();

    @BeforeClass
    public void initialize() {
        this.allEmployeesData = new AllEmployeesData();
    }

    @Test
    public void testGetAllEmployeeResources() {
        ValidatableResponse validatableResponse = this.allEmployeesData.getAllEmployeeResources();
        //System.out.println(validatableResponse.extract().response().getBody().prettyPeek());
        assertThat(validatableResponse.extract().statusCode(), equalTo(HttpStatus.SC_OK));
        System.out.println(validatableResponse.statusCode(200));
    }

    @Test
    public void testCreateAnEmployeeRecord() {
        emp.setEmpEmail("employee1@pnt.com");
        emp.setEmpName("Homer Simpson");
        emp.setSalary("90k");
        emp.setDepartment("Nothing");

        ValidatableResponse validatableResponse =
                this.allEmployeesData.createAnEmployeeRecord(emp);
        assertThat(validatableResponse.extract().statusCode(), equalTo(HttpStatus.SC_OK));
        String expectedName = validatableResponse.extract().body().path("empName");
        assertThat(emp.getEmpName(), is(expectedName));
    }

    @Test
    public void testGetAllEmployeeResourcesResponse() {
        Response response = this.allEmployeesData.getAllEmployeeResourcesResponse();
        
//        if (response.statusCode() == HttpStatus.SC_OK) {
//            writeToEmplyeePojo(response);
//            readFromEmployeePojo();
//        }
    }

    public void writeToEmplyeePojo(Response response) {
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(response.asString());
        JsonArray jsonArray = root.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                JsonObject jsonobject = jsonArray.get(i).getAsJsonObject();
                if (jsonobject.get("empEmail") != null)
                    emp.setEmpEmail(jsonobject.get("empEmail").toString());
                else
                    emp.setEmpEmail("");
                if (jsonobject.get("empName") != null)
                    emp.setEmpName(jsonobject.get("empName").toString());
                else
                    emp.setEmpName("");
                if (jsonobject.get("salary") != null)
                    emp.setSalary(jsonobject.get("salary").toString());
                else
                    emp.setSalary("");
                if (jsonobject.get("department") != null)
                    emp.setDepartment(jsonobject.get("department").toString());
                else
                    emp.setDepartment("");
                empList.add(new Employees(emp.getEmpEmail(), emp.getEmpName(), emp.getSalary(), emp.getDepartment()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void readFromEmployeePojo() {
        for (Employees emps : empList) {
            System.out.println("Emplyee Name: " + emps.getEmpName() +
                    " Employee Email: " + emps.getEmpEmail() +
                    " Employee Salary: " + emps.getSalary() +
                    " Employee Department: " + emps.getDepartment());
        }
    }
}
