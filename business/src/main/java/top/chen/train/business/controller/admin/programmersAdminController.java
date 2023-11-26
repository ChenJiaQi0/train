package top.chen.train.business.controller.admin;

import top.chen.train.common.context.LoginMemberContext;
import top.chen.train.common.resp.CommonResp;
import top.chen.train.common.resp.PageResp;
import top.chen.train.business.req.programmersQueryReq;
import top.chen.train.business.req.programmersSaveReq;
import top.chen.train.business.resp.programmersQueryResp;
import top.chen.train.business.service.programmersService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/programmers")
public class programmersAdminController {

@Resource
private programmersService programmersService;

@PostMapping("/save")
public CommonResp<Object> save(@Valid @RequestBody programmersSaveReq req) {
    programmersService.save(req);
    return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<programmersQueryResp>> queryList(@Valid programmersQueryReq req) {
        PageResp<programmersQueryResp> list = programmersService.queryList(req);
            return new CommonResp<>(list);
            }

            @DeleteMapping("/delete/{id}")
            public CommonResp<Object> delete(@PathVariable Long id) {
                programmersService.delete(id);
                return new CommonResp<>();
                }

                }