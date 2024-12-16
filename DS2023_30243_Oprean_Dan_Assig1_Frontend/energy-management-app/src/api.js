import axios from "axios";

const API_BASE_URL_USERS = "http://172.30.0.3:3000";
const API_BASE_URL_DEVICES = "http://172.30.0.2:3003";

const apiUsers = axios.create({
  baseURL: API_BASE_URL_USERS,
});

const apiDevices = axios.create({
  baseURL: API_BASE_URL_DEVICES,
});

export default { apiDevices, apiUsers };
