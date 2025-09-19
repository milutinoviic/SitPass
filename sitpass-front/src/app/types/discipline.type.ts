export interface Discipline {
  id: number;
  name: string;
}

export interface DisciplineToFromFacility {
  facilityId: number;
  disciplineIds: number[];
}

export interface CreateDiscipline {
  name: string;
}

