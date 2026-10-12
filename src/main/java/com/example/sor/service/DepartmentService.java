
package com.example.sor.service;

import com.example.sor.entity.Department;
import com.example.sor.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department create(Department department) {
        department.setId(null);
        return departmentRepository.save(department);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with ID: " + id
                ));
    }

    public Department update(Long id, Department updatedDepartment) {
        Department existingDepartment = getById(id);

        existingDepartment.setName(updatedDepartment.getName());

        return departmentRepository.save(existingDepartment);
    }

    public void delete(Long id) {
        Department department = getById(id);
        departmentRepository.delete(department);
    }
}
