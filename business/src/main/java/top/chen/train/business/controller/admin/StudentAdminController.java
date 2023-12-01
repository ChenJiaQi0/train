package top.chen.train.business.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.chen.train.business.domain.Student;
import top.chen.train.business.req.StudentQueryReq;
import top.chen.train.business.req.StudentSaveReq;
import top.chen.train.business.resp.StudentQueryResp;
import top.chen.train.business.service.StudentService;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.common.resp.PageResp;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/student")
public class StudentAdminController {

@Resource
private StudentService studentService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody StudentSaveReq req) {
    studentService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StudentQueryResp>> queryList(@Valid StudentQueryReq req) {
        PageResp<StudentQueryResp> list = studentService.queryList(req);
            return new CommonResp<>(list);
            }
    @GetMapping("/query-all")
    public CommonResp<List<Student>> queryAll(@RequestParam(value = "school",required = false)String school){
        List<Student> students = new ArrayList<>();
        System.out.println(school);
        if (ObjectUtil.isNotNull(school)) {
            students = studentService.queryAll(school);
        } else {
            students = studentService.queryAll(null);
        }
        return new CommonResp<>(students);
    }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                studentService.delete(id);
                return new CommonResp<>();
                }

                }