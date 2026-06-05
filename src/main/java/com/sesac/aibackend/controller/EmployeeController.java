package com.sesac.aibackend.controller;

import com.sesac.aibackend.domain.Department;
import com.sesac.aibackend.domain.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final List<Employee> employeeList = new ArrayList<>();

    private final Department devTeam = new Department(1L, "개발팀");
    private final Department hrTeam = new Department(2L, "인사팀");

    public EmployeeController() {
        employeeList.add(new Employee(1L, "홍길동", devTeam)); // 홍길동은 개발팀
        employeeList.add(new Employee(2L, "김철수", devTeam)); // 김철수도 개발팀
        employeeList.add(new Employee(3L, "이영희", hrTeam));  // 이영희는 인사팀
}

    @PostMapping("/employees")
    public String createEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return employee.getName() + " 직원이 성공적으로 등록되었습니다.";
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestParam String newName) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                emp.setName(newName);
                return id + "번 직원의 이름을 " + newName + "(으)로 변경했습니다.";
            }
        }
        return "해당 ID의 직원을 찾을 수 없습니다.";
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        for (Employee emp : employeeList) {
            if (emp.getId().equals(id)) {
                employeeList.remove(emp);
                return id + "번 직원이 퇴사(삭제) 처리되었습니다.";
            }
        }
        return "해당 ID의 직원을 찾을 수 없습니다.";
    }

    @GetMapping("/departments/{deptName}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable String deptName) {
        List<Employee> result = new ArrayList<>();

        for (Employee emp : employeeList) {
            // 직원의 부서 이름이 주소창으로 들어온 부서 이름과 같으면 리스트에 담음
            if (emp.getDepartment() != null && emp.getDepartment().getName().equals(deptName)) {
                result.add(emp);
            }
        }
        return result;
    }
}

