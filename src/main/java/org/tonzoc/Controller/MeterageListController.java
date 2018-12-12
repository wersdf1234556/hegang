package org.tonzoc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.PageList;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Mapper.MeterageListMapper;
import org.tonzoc.Model.MeterageList;

@RestController
@RequestMapping("meterageLists")
public class MeterageListController extends BaseApiService {

	@Autowired
	private MeterageListMapper meterageListMapper;

	@RequestMapping("list")
	public ResponseBase list() {
		try {
			List<MeterageList> myList = meterageListMapper.list();
			PageList pageList = new PageList(myList.size(), myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}
	@RequestMapping("listByParentId")
	private List listByParentId(@RequestParam(value = "parentId", defaultValue = "") String parentId) {
		List<MeterageList> myList = meterageListMapper.listByParentId(parentId);

		for (MeterageList meterageList : myList) {
			meterageList.setLabel(meterageList.getListNo() + " " + meterageList.getItemName());
			meterageList.setChildren(listByParentId(meterageList.getListNo()));
		}

		return myList;
	}

	@RequestMapping("listByLevel")
	public ResponseBase listByLevel() {
		try {
			List<MeterageList> myList = this.listByParentId("0");
			return setResultSuccessWithData(myList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}
}
