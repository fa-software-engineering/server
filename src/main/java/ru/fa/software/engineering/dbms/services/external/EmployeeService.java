package ru.fa.software.engineering.dbms.services.external;


import ru.fa.software.engineering.dbms.repositories.external.EmployeeRepository;
import ru.fa.software.engineering.dto.internal.SkillDto;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class EmployeeService {
    @Inject
    EmployeeRepository employeeRepository;

    public List<SkillDto> getSkillsByEmployeeId(String employeeId) {
        return ModelMapperUtil.mapList(employeeRepository.findSkillsByEmployeeId(employeeId), SkillDto.class);
    }

    public boolean addSkillByEmployeeId(long skillId, String employeeId) {
        return employeeRepository.addSkillByEmployeeId(skillId, employeeId);
    }

    public boolean deleteSkillByEmployeeId(String employeeId, long skillId) {
        return employeeRepository.deleteSkillByEmployeeId(employeeId, skillId);
    }
}
