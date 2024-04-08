import request from "../../../utils/request";

export function queryAll(sysUsers){
  return request({
    url: "/sysUsers/query/page",
    method: "GET",
    params: sysUsers
  })
}

export function query(sysUsers){
  return request({
    url: "/sysUsers/query",
    method: "GET",
    params: sysUsers
  })
}

export function queryByPage(sysUsers, page, pageSize){
  return request({
    url: "/sysUsers/query/page",
    method: "GET",
    params: {
      sysUsers,
      "page" : page,
      "pageSize" : pageSize
    }
  })
}

export function addUser(sysUsers){
  console.log(sysUsers);
  return request({
    url: "/sysUsers/add",
    method: "POST",
    data: sysUsers
  })
}

export function getToken(){
  return request({
    url: "/sysUsers/token",
    method: "GET"
  })
}

export function delUser(userId){
  return request({
    url: "/sysUsers/delete/"+ userId,
    method: "DELETE"
  })
}

export function updateUser(sysUsers){
  return request({
    url: "/sysUsers/edit",
    method: "PUT",
    data: sysUsers
  })
}
