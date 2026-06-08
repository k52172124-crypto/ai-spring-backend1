package com.sesac.aibackend.controller;

import com.sesac.aibackend.domain.Department;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmnetController {
    private final List<Department> departmentList = new ArrayList<>();

    public DepartmnetController() {
        // 초기 데이터 세팅
        departmentList.add(new Department(1L, "개발팀"));
        departmentList.add(new Department(2L, "인사팀"));
        departmentList.add(new Department(3L, "영업팀"));
    }

    // 1. 모든 부서 목록 조회
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentList;
    }

    // 2. 새 부서 등록 (CRUD 중 Create)
    @PostMapping
    public String createDepartment(@RequestBody Department department) {
        departmentList.add(department);
        return department.getName() + " 부서가 등록되었습니다.";
    }

    // 3. 특정 부서 삭제 (Delete)
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentList.removeIf(dept -> dept.getId().equals(id));
        return id + "번 부서가 삭제되었습니다.";
    }
}
