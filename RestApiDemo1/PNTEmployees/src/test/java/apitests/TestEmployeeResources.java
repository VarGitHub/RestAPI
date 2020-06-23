package apitests;

import apiclient.EmployeeResources;
import apiclient.EmployeesPojo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestEmployeeResources {

    EmployeeResources employeeResources =  new EmployeeResources();
    EmployeesPojo employeesPojo = new EmployeesPojo();
    List<EmployeesPojo> employeesList = new ArrayList<EmployeesPojo>();

    @Test
    public void testGetAllEmployeeResources() {
        ValidatableResponse validatableResponse = this.employeeResources.getAllEmployeesData();
        Response response = validatableResponse.extract().response();
        if (response.statusCode() == HttpStatus.SC_OK) {
            writeToEmployeesPojo(response);
            readFromEmployeesPojo();
        }
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_OK));
    }

    public void writeToEmployeesPojo(Response response) {
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(response.asString());
        JsonArray jsonArray = root.getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            if (jsonObject.get("empEmail") != null)
                employeesPojo.setEmpEmail(jsonObject.get("empEmail").toString());
            else
                employeesPojo.setEmpEmail("");
            if (jsonObject.get("empName") != null)
                employeesPojo.setEmpName(jsonObject.get("empName").toString());
            else
                employeesPojo.setEmpName("");
            if (jsonObject.get("salary") != null)
                employeesPojo.setSalary(jsonObject.get("salary").toString());
            else
                employeesPojo.setSalary("");
            if (jsonObject.get("department") != null)
                employeesPojo.setDepartment(jsonObject.get("department").toString());
            else
                employeesPojo.setDepartment("");
            employeesList.add(new EmployeesPojo(employeesPojo.getEmpEmail(), employeesPojo.getEmpName(), employeesPojo.getSalary(), employeesPojo.getDepartment()));
        }
    }

    public void readFromEmployeesPojo() {
        for (EmployeesPojo empData: employeesList) {
            System.out.println("Employee Name: " + empData.getEmpName());
        }
    }
}
