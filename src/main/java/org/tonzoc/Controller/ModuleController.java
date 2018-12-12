package org.tonzoc.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.PageList;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Mapper.ModuleMapper;
import org.tonzoc.Model.Module;

@RestController
@RequestMapping("modules")
public class ModuleController extends BaseApiService {

	@Autowired
	private ModuleMapper moduleMapper;

	@RequestMapping("list")
	public ResponseBase list() {
		try {
			List<Module> myList = moduleMapper.list();
			return setResultSuccessWithData(myList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}

	@RequestMapping("listByParentId")
	public ResponseBase listByParentId(@RequestParam(value = "parentId", defaultValue = "0") String parentId) {
		try {

			List<Module> myList = moduleMapper.listByParentId(Long.parseLong(parentId));
			for (Module module : myList) {
				module.setChildren(moduleMapper.listByParentId(module.getId()));
			}
			PageList pageList = new PageList(myList.size(), myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}
}
