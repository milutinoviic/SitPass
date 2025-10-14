// export interface RangeDTO {
//   min: number;
//   max: number;
// }

// export interface SearchQueryDTO {
//   keywords?: string[];          // simple search
//   expression?: string[];        // advanced search
//   ranges?: { [key: string]: RangeDTO };  // map from field -> range
//   isAsc: boolean;
// }

// export interface FacilityIndex {
//   id: string;
//   name: string;
//   descriptionEn: string;
//   descriptionSr: string;
//   fileDescriptionEn: string;
//   fileDescriptionSr: string;
//   serverFilename: string;
//   reviewCount: number;
//   avgEquipmentGrade: number;
//   avgStaffGrade: number;
//   avgHygieneGrade: number;
//   avgSpaceGrade: number;
// }

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
