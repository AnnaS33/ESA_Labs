import axios from 'axios'

// axios.defaults.baseURL
const backend = axios.create({
    baseURL: 'http://localhost:8080'
})

export default backend