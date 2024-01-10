import axios from "axios"

export const api = axios.create({
    baseURL: "http://localhost:8080"
})


export const getHeader = () => {
    const token = localStorage.getItem("token")
    return {
		Authorization: `Bearer ${token}`,
		"Content-Type": "application/json"
    }
}

/* Función para registrar un usuario */
export async function registerUser(registration){
    try {
        const response = await api.post("/auth/register-user", registration)
        return response.data
    } catch (error) {
        if (error.response && error.response.data) {
            throw new Error(error.response.data)
        } else {
            throw new Error(`User registration error : ${error.message}`)
        }
    }
}

/* Función para logear un usuario */
export async function loginUser(login){
    try {
        const response = await api.post("/auth/login", login)
        if (response.status >= 200 && response.status < 300) {
            return response.data
        } else {
            return null
        }       
    } catch (error) {
        console.error(error)
        return null        
    }
}


export async function getUserProfile(userId, token) {
	try {
		const response = await api.get(`users/profile/${userId}`, {
			headers: getHeader()
		})
		return response.data
	} catch (error) {
		throw error
	}
}

export async function deleteUser(userId) {
	try {
		const response = await api.get(`users/delete/${userId}`, {
			headers: getHeader()
		})
		return response.data
	} catch (error) {
		throw error
	}
}

export async function getUser(userId, token) {
	try {
		const response = await api.get(`users/${userId}`, {
			headers: getHeader()
		})
		return response.data
	} catch (error) {
		throw error
	}
}

/*Funcion para crear un pack*/ 
export async function createPack(packName, image) {
	const formData = new FormData()
	formData.append("packName", packName)
	formData.append("image", image)


	const response = await api.post("/packs/createPack", formData,{
		headers: getHeader()
	})
	if (response.status === 201) {
		return true
	} else {
		return false
	}
}