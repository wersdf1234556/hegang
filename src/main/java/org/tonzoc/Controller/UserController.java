package org.tonzoc.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.PageList;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.WhereParam;
import org.tonzoc.Mapper.UserMapper;
import org.tonzoc.Model.User;

@RestController
@RequestMapping("users")
public class UserController extends BaseApiService {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("list")
	public ResponseBase list() {
		try {
			List<User> myList = userMapper.list();
			System.out.println(myList);
			return setResultSuccessWithData(myList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}

	}

	//validate:认证
	@RequestMapping("validate")
	public ResponseBase validate(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password) {
		try {
			List myList = userMapper.validate(name, password);
			PageList pageList = new PageList(myList.size(), myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}

	@RequestMapping("setPassword")
	public ResponseBase setPassword(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "password", defaultValue = "") String password) {
		try {
			int result = userMapper.setPassword(name, password);
			PageList pageList = new PageList(result, new ArrayList());
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}

	@RequestMapping("add")
	public ResponseBase add(
			@RequestParam(value = "code", defaultValue = "") String code,
			@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "staffName", defaultValue = "") String staffName,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "email", defaultValue = "") String email) {
		try {

//            List<User> myList = userMapper.listCode();
//            System.out.println(myList);
            int result = userMapper.insert(code, Long.parseLong(roleId), name, staffName, mobile, birthday, Long.parseLong(gender), email);

			if(result<1) {
				return setInsertResultError();
			}
            return setResultSuccess("添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setInsertResultError();
		}
	}

	@RequestMapping("listPaged")
	public ResponseBase listPaged(
			@RequestParam(value = "name", defaultValue = "") String name,
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
			List<User> myList = userMapper.listPaged(selectParam);
			int total = userMapper.countPaged(selectParam);
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
			int result = userMapper.delete(Long.parseLong(id));
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
			@RequestParam(value = "code", defaultValue = "") String code,
			@RequestParam(value = "roleId", defaultValue = "") String roleId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "staffName", defaultValue = "") String staffName,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "birthday", defaultValue = "") String birthday,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "email", defaultValue = "") String email) {
		try {
			System.out.println("update:" + id);
			int result = userMapper.update(Long.parseLong(id), code, Long.parseLong(roleId), name, staffName, mobile, birthday, Long.parseLong(gender), email);
			if(result<1) {
				return setUpdateResultError();
			}
			return setResultSuccess("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setUpdateResultError();
		}
	}
}
