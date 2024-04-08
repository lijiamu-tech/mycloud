import request from "../../../utils/request";

export function queryAll(device){
  return request({
    url: "/device/query",
    method: "GET",
    params: device
  })
}

export function query(device){
  return request({
    url: "/device/query/page",
    method: "GET",
    params: device
  })
}
export function queryByPage(device, page, pageSize){
  return request({
    url: "/device/query/page",
    method: "GET",
    params:{
      device,
      pageNum: page,
      pageSize: pageSize
    }
  })
}

export function addDevice(device) {
  return request({
    url: "/device/add",
    method: "POST",
    data: device
  })
}

export function updateDevice(device){
  return request({
    url: "/device/edit",
    method: "PUT",
    data: device
  })
}

export function deleteDevice(ids){
  return request({
    url: "/device/delete/" + ids,
    method: "DELETE"
  })
}
