import axios from "axios";

export const addCategory = async (category) => {
    return await axios.post(`http://localhost:8080/api/v1.0/categories`, category);
};

export const deleteCategory = async(categoryID) => {
   return await axios.post(`http://localhost:8080/api/v1.0/categories/${categoryID}`, categoryID);
}

export const fetchallCategory = async()=>{
    return await axios.post(`http://localhost:8080/api/v1.0/categories`);
}