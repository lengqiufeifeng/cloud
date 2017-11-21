package logan.exemple.service.monitoring.action;

import logan.exemple.service.monitoring.common.Result;
import logan.exemple.service.monitoring.common.StatusCode;
import logan.exemple.service.monitoring.model.SysLog;
import logan.exemple.service.monitoring.service.SysLogService;
import logan.exemple.service.monitoring.vo.SysLogVo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiufeng on 2017/9/19.
 */
@RestController
@RequestMapping("/log")
public class SysLogAction {
    @Resource
    SysLogService sysLogService;

    @RequestMapping(value = "addlog", method = RequestMethod.POST)
    public Result recordLog(@RequestBody SysLog sysLog) throws Exception {
        Result result = new Result();
        SysLog num = sysLogService.saveSysLog(sysLog);

        if (null != num) {
            result.setResultCode(StatusCode.Success.getIndex());
        } else {
            result.setResultCode(StatusCode.Fail.getIndex());
        }
        return result;
    }

    @RequestMapping(value = "getlogs/{page}/{size}")
    public Result getlogs(@RequestBody(required = false) SysLogVo sysLogVo, @PathVariable int page, @PathVariable int size) throws Exception {
        Result result = new Result();
        Page<SysLog> lt = sysLogService.findBySysLogVo(sysLogVo, size, page);
        result.setResultCode(StatusCode.Success.getIndex());
        Map map=new HashMap();
        map.put("totalRows",lt.getTotalElements());
        map.put("totalPages",lt.getTotalPages());
        map.put("currentlyRow",lt.getSize());
        map.put("current",lt.getNumber());
        map.put("rows",lt.getContent());
        map.put("currentPage",page);
        result.setDataContent(map);
        return result;
    }

    @RequestMapping(value = "/service")
    public Result service() throws Exception {
        Result result = new Result();

        result.setResultCode(StatusCode.Success.getIndex());
        if (true)
            throw new Exception("");
        return result;
    }
}
