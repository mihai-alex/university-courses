import { Course } from "./Course";

export interface Professor {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  salary: number;
  courses?: Course[];
}
