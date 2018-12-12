package org.tonzoc.Controller;

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
import org.tonzoc.Mapper.RoleBindMapper;
import org.tonzoc.Mapper.RoleMapper;
import org.tonzoc.Model.Role;

@RestController
@RequestMapping("roles")
public class RoleController extends BaseApiService {

	@Autowired
	private RoleMapper roleMapper;
    @Autowired
    private RoleBindMapper roleBindMapper;

	@RequestMapping("list")
	public ResponseBase list() {
		try {
			List<Role> myList = roleMapper.list();
			PageList pageList = new PageList(myList.size(), myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}

	}

	@RequestMapping("add")
	public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "moduleCommaList", defaultValue = "") String moduleCommaList) {
		try {
			int result = roleMapper.insert(name);
            String[] modules=moduleCommaList.split(",");
            System.out.println(modules);
            Role selectId = roleMapper.selectId(name);
            long roleId =selectId.getId();
            int result1 = roleBindMapper.delete(roleId);
            //取出所获的moduleId
            for (int i = 0; i < modules.length; i++) {
                int result2 = roleBindMapper.insert(roleId,Long.parseLong(modules[i]));
                System.out.println(result2);
                if (result2 < 1) {
                    //修改失败
                    return setUpdateResultError();
                }
            }
			if (result < 1) {
				return setInsertResultError();
			}
			return setResultSuccess("添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setInsertResultError();
		}

	}

	@RequestMapping("listPaged")
	public ResponseBase listPaged(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "sort", defaultValue = "desc") String sort,
			@RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
			@RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {
		try {
			List<WhereParam> whereParams = new ArrayList<>();
			WhereParam whereParam = new WhereParam("name", "like", name);
			whereParams.add(whereParam);
			SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
					Integer.parseInt(pageIndex), whereParams);
			System.out.println(order);
			System.out.println(sort);
			List<Role> myList = roleMapper.listPaged(selectParam);
			int total = roleMapper.countPaged(selectParam);
			
			PageList pageList = new PageList(total, myList);
			return setResultSuccessWithData(pageList);
			
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}

	@RequestMapping("delete")
	public ResponseBase delete(@RequestParam(value = "id", defaultValue = "") String id) {
		try {
			System.out.println("delete:" + id);
			int result = roleMapper.delete(Long.parseLong(id));
			if(result<1) {
				return setDeleteResultError();
			}
			return setResultSuccess("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setDeleteResultError();
		}
	}

	@RequestMapping("edit")
	public ResponseBase edit(
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "moduleCommaList", defaultValue = "") String moduleCommaList) {
		try {
			System.out.println("update:" + id);
			int result = roleMapper.update(Long.parseLong(id), name);
			if(result<1) {
				return setUpdateResultError();
			}
            String[] modules=moduleCommaList.split(",");
            System.out.println(modules);
            Role selectId = roleMapper.selectId(name);
            long roleId =selectId.getId();
            int result1 = roleBindMapper.delete(roleId);
            //取出所获的moduleId
            for (int i = 0; i < modules.length; i++) {
                int result2 = roleBindMapper.insert(roleId,Long.parseLong(modules[i]));
                System.out.println(result2);
                if (result2 < 1) {
                    //修改失败
                    return setUpdateResultError();
                }
            }
			return setResultSuccess("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setUpdateResultError();
		}
	}
	//批量
	@RequestMapping("batchUpdate")
	public ResponseBase update(@RequestParam(value = "roleId", defaultValue = "") String roleId,
							   @RequestParam(value = "moduleCommaList", defaultValue = "") String moduleCommaList) {
		try {
			String[] modules=moduleCommaList.split(",");
			System.out.println(modules);
			int result = roleBindMapper.delete(Long.parseLong(roleId));
			//取出所获的moduleId
			for (int i = 0; i < modules.length; i++) {
				int result1 = roleBindMapper.insert(Long.parseLong(roleId),Long.parseLong(modules[i]));
				System.out.println(result1);
				if (result1 < 1) {
					//修改失败
					return setUpdateResultError();
				}
			}
			return setResultSuccess("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setUpdateResultError();
		}
	}
}
