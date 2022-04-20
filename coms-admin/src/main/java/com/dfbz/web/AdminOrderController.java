package com.dfbz.web;

import com.dfbz.domain.*;
import com.dfbz.service.*;
import com.dfbz.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***
 * 设计订单的请求页面
 */
@RestController
@RequestMapping("/admin")
public class AdminOrderController {

    //分页默认条数
    private static final String DEFAULT_PAGE_SIZE = "12";

    @Resource
    private ComsOrderService orderService;
    @Resource
    private ComsStallService stallService;
    @Resource
    private ComsOrderItemService orderItemService;
    @Resource
    private ComsSupplierService supplierService;
    @Resource
    private ComsProductService productService;



    /***
     * 查询所有商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/order/orderList")
    public Object getOrderList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ComsOrder> orders = orderService.listAll();
        PageInfo<ComsOrder> pageInfo = new PageInfo<ComsOrder>(orders);
        List<ComsStall> stall = stallService.listAllStall();
        ModelAndView mv = new ModelAndView("order_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("stalls", stall);
        return mv;
    }


    /***
     * 通过订单id查询订单内商品
     * @param orderId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/order/orderItem")
    public Object getOrderList(@RequestParam(value = "orderId") Integer orderId,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ComsOrderItem> orderItems = orderItemService.selectByOrderId(orderId);
        PageInfo<ComsOrderItem> pageInfo = new PageInfo<ComsOrderItem>(orderItems);
        ModelAndView mv = new ModelAndView("order_item");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        return mv;

    }

    /***
     * 查询所有订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/order/allOrderItem")
    public Object getAllOrderList(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<ComsOrderItem> orderItems = orderItemService.selectOrderItems();

        PageInfo pageInfo = new PageInfo(orderItems);
        ModelAndView mv = new ModelAndView("allOrderItem_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        return mv;

    }

    /***
     * 按订单id查stall详情
     * @param orderId
     * @return
     */
    @GetMapping("/order/findStallByOrder")
    public Object findStallByOrder(@RequestParam(value = "orderId") Integer orderId) {
        ComsStall stall = orderService.findStallByOrder(orderId);
        return stall;
    }


    /***
     * 按供应商模糊查找订单
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/order/queryByName")
    public Object queryByName(@RequestParam(value = "keyWord") String keyWord,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ComsOrder> orders = orderService.findOrderBySup(keyWord);
        List<ComsStall> stall = stallService.listAllStall();
        PageInfo<ComsOrder> pageInfo = new PageInfo<ComsOrder>(orders);
        ModelAndView mv = new ModelAndView("order_list");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("stalls", stall);
        return mv;


    }


    /***
     * 订单内项目模糊搜索
     * @param itemName
     * @param orderId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/item/queryByName")
    public Object queryByName(@RequestParam(value = "itemName") String itemName,
                              @RequestParam(value = "orderId") Integer orderId,
                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ComsOrderItem> orderItems = null;
        if (itemName.equals("") || itemName.trim().length() == 0) {
            orderItems = orderItemService.selectByOrderId(orderId);
        } else {
            orderItems = orderItemService.queryItemByName(itemName, orderId);
        }
        PageInfo<ComsOrderItem> pageInfo = new PageInfo<ComsOrderItem>(orderItems);
        ModelAndView mv = new ModelAndView("order_item");
        ComsAdmin admin = (ComsAdmin) SecurityUtils.getSubject().getPrincipal();
        mv.addObject("admin", admin);
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }


    /***
     * 查询所有档口
     * @return
     */
    @GetMapping("/stall/getAllStall")
    public Object getAllStall() {
        List<ComsSupplier> suppliers = supplierService.listAllSupplier();
        return suppliers;

    }


    /***
     * 导出Excel
     * @param startDate
     * @param endDate
     * @param supId
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(@RequestParam(value = "startDate") String startDate,
                            @RequestParam(value = "endDate") String endDate,
                            @RequestParam(value = "supId") Integer supId,
                            HttpServletResponse res) throws IOException {

        //前台传入时间为String，需要进行格式化处理再去查询
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime=LocalDateTime.now().format(dateTimeFormatter).replaceAll("[-: ]","");

        //excel导出路径
        ComsSupplier sup_temp = supplierService.findById(supId);
        String filePath = sup_temp.getName() + "_" + startDate + "至" + endDate + "_" + dateTime + ".xlsx";

        String[] split = startDate.split("-");
        String[] split1 = endDate.split("-");

        split[2] = split[2].length() == 1 ? "0" + split[2] : split[2];
        split[1] = split[1].length() == 1 ? "0" + split[1] : split[1];
        split1[2] = split1[2].length() == 1 ? "0" + split1[2] : split1[2];
        split1[1] = split1[1].length() == 1 ? "0" + split1[1] : split1[1];

        startDate = split[0] + "-" + split[1] + "-" + split[2] + " 00:00:00";
        endDate = split1[0] + "-" + split1[1] + "-" + split1[2] + " 00:00:00";


        LocalDateTime startDateTime = LocalDateTime.parse(startDate, dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, dateTimeFormatter);

        //查询要导出的数据
        List<ComsOrderItem> comsOrderItems = orderItemService.queryOrderItems(startDateTime, endDateTime, supId);


        //创建Excel文件(Workbook)
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建工作表(Sheet)
        XSSFSheet sheet = workbook.createSheet("Sheet1");


        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("商品名");
        row.createCell(2).setCellValue("数量");
        row.createCell(3).setCellValue("单价");
        row.createCell(4).setCellValue("生产日期");
        row.createCell(5).setCellValue("保质期");
        row.createCell(6).setCellValue("品牌");
        row.createCell(7).setCellValue("供货商");
        ComsSupplier supplier = supplierService.findById(supId);
        Integer i = 1;
        for (ComsOrderItem items : comsOrderItems) {
            XSSFRow newRow = sheet.createRow(i);
            ComsProduct products = productService.findById(items.getProdId());

            newRow.createCell(0).setCellValue(i);
            newRow.createCell(1).setCellValue(items.getProdName());
            newRow.createCell(2).setCellValue(items.getNum());
            newRow.createCell(3).setCellValue(items.getCurrentMoney().toString());
            newRow.createCell(4).setCellValue(products.getPd().toString());
            newRow.createCell(5).setCellValue(products.getFd());
            newRow.createCell(6).setCellValue(products.getBrand());
            newRow.createCell(7).setCellValue(supplier.getName());
            i++;
        }
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流




        /***
         * 返回前端下载
         */
        File excelFile = new File(filePath);

        res.setCharacterEncoding("UTF-8");

        String realFileName = excelFile.getName();

        res.setHeader("content-type", "application/octet-stream;charset=UTF-8");

        res.setContentType("application/octet-stream;charset=UTF-8");

        //加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”

        res.addHeader("Content-Length", String.valueOf(excelFile.length()));


        try {

            res.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realFileName.trim(), "UTF-8"));

        } catch (UnsupportedEncodingException e1) {

            e1.printStackTrace();

        }

        byte[] buff = new byte[1024];

        BufferedInputStream bis = null;

        OutputStream os = null;

        try {

            os = res.getOutputStream();

            bis = new BufferedInputStream(new FileInputStream(excelFile));

            int i1 = bis.read(buff);

            while (i1 != -1) {

                os.write(buff, 0, buff.length);

                os.flush();

                i1 = bis.read(buff);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (bis != null) {

                try {

                    bis.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }
}
