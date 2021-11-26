package com.dfbz.web;

import com.dfbz.domain.ComsStall;
import com.dfbz.domain.ComsStallExample;
import com.dfbz.service.ComsStallService;
import com.dfbz.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/wx/stall")
public class ComsStallComtroller {

    @Resource
    ComsStallService stallService;

    /**
     * 显示所有档口
     * @return
     */
    @RequestMapping("listAll")
    public Object listAllStall(){
        return stallService.listAllStall();
    }

    /***
     * 档口状态
     * @param id
     * @param statusId
     * @return
     */
    @RequestMapping("editStatus")
    public Integer editStatus(@RequestParam(value = "id")Integer id,@RequestParam("status")Integer statusId){
        return stallService.editStatus(id,statusId);
    }

    /***
     * 按id查档口
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public ComsStall findById(Integer id){
        return stallService.findById(id);
    }


    /**
     * 第二版
     */

    @RequestMapping("/login")
    public Object login(@RequestBody Map map){
        String phone = (String) map.get("phone");
        String password = (String) map.get("password");
        ComsStallExample example = new ComsStallExample();
        example.or().andPhoneEqualTo(phone);
        List<ComsStall> stalls = stallService.findByPhone(example);
        if(stalls.size()==0){
            return ResponseUtil.noAccount();
        }else if(stalls.size()>1){
            return ResponseUtil.serious();
        }else{
            if (!stalls.get(0).getPassword().equals(password)) {
                return ResponseUtil.errorPassword();
            }else{
                if (stalls.get(0).getStatus().equals(0)) {
                    return ResponseUtil.freezeAccount();
                }
                return ResponseUtil.ok(stalls.get(0));
            }

        }

    }

    @RequestMapping("/getTime")
    public Object getTime(){
        Map<String,Long> map = new HashMap<>();
        Date nowTime = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nowTime);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date endTime = new Date(calendar.getTime().getTime());
        map.put("time",endTime.getTime()-nowTime.getTime());
        return ResponseUtil.ok(map);
    }
}
