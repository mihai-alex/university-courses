import axios from "axios";
import { Professor } from "../models/Professor";

const PROFESSOR_API_BASE_URL = "http://localhost:8080/professors";

class ProfessorService {
  getAllProfessors(): Promise<any> {
    return axios.get(PROFESSOR_API_BASE_URL);
  }

  createProfessor(professor: Professor): Promise<any> {
    return axios.post(PROFESSOR_API_BASE_URL, professor);
  }
}

export default new ProfessorService();
