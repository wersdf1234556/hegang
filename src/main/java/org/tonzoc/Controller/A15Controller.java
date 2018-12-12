package org.tonzoc.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.PageList;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.WhereParam;
import org.tonzoc.Mapper.A15Mapper;
import org.tonzoc.Model.A15;

@RestController
@RequestMapping("a15s")
public class A15Controller extends BaseApiService {

	@Autowired
	private A15Mapper a15Mapper;

	@RequestMapping("save")
	public ResponseBase add(@RequestParam(value = "meterageListId", defaultValue = "0") String meterageListId,
			@RequestParam(value = "startSection", defaultValue = "") String startSection,
			@RequestParam(value = "endSection", defaultValue = "") String endSection,
			@RequestParam(value = "part", defaultValue = "") String part,
			@RequestParam(value = "pictureNo", defaultValue = "") String pictureNo,
			@RequestParam(value = "certNo", defaultValue = "") String certNo,
			@RequestParam(value = "imageUrl", defaultValue = "") String imageUrl,
			@RequestParam(value = "formula", defaultValue = "") String formula,
			@RequestParam(value = "num", defaultValue = "0.000") String num,
			@RequestParam(value = "date", defaultValue = "") String date,
			@RequestParam(value = "creatorUserId", defaultValue = "0") String creatorUserId,
			@RequestParam(value = "status", defaultValue = "") String status) {
		try {
			int result = a15Mapper.insert(Long.parseLong(meterageListId), startSection, endSection, part, pictureNo,
					certNo, imageUrl, formula, new BigDecimal(num), date, Long.parseLong(creatorUserId),status);
			if (result < 1) {
				// 添加失败
				return setInsertResultError();
			}
			return setResultSuccess("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setInsertResultError();
		}
	}

	@RequestMapping("listPaged")
	public ResponseBase listPaged(@RequestParam(value = "listNo", required = false,defaultValue = "") String listNo,
								  @RequestParam(value = "creatorUserId", required = false,defaultValue = "") String creatorUserId,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "sort", defaultValue = "desc") String sort,
			@RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

		try {
			List<WhereParam> whereParams = new ArrayList<>();
			WhereParam whereParam = new WhereParam("m.listNo", "like", listNo);
			whereParams.add(whereParam);
			WhereParam whereParam2 = new WhereParam("a.creatorUserId", "=", creatorUserId);
			whereParams.add(whereParam2);
			SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
					Integer.parseInt(pageIndex), whereParams);
			System.out.println(order);
			System.out.println(sort);
			List<A15> myList = a15Mapper.listPaged(selectParam);
			int total = a15Mapper.countPaged(selectParam);
			PageList pageList = new PageList(total, myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}
}
