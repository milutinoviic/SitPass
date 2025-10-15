
export interface RangeDTO {
  min: number | null;
  max: number | null;
}

export interface SearchQueryDTO {
  keywords: string[];
  expression: string[];
  ranges: { [key: string]: RangeDTO };
  isAsc: boolean;
}

export interface FacilityIndex {
  id: string;
  name: string;
  descriptionEn: string;
  descriptionSr: string;
  fileDescriptionEn: string;
  fileDescriptionSr: string;
  serverFilename: string;
  reviewCount: number;
  avgEquipmentGrade: number;
  avgStaffGrade: number;
  avgHygieneGrade: number;
  avgSpaceGrade: number;
}
