import { Component } from '@angular/core';
import { FacilityIndex, SearchQueryDTO } from '../../types/index.type';
import { SearchService } from '../../services/search/search.service';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../../services/config.service';

@Component({
  selector: 'app-search',
  standalone: false,
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {

  // SIMPLE SEARCH
  simpleQuery: string = '';
  simpleRanges: any = {
    reviewCount: { min: null, max: null },
    avgEquipmentGrade: { min: null, max: null },
    avgStaffGrade: { min: null, max: null },
    avgHygieneGrade: { min: null, max: null },
    avgSpaceGrade: { min: null, max: null },
  };

  // ADVANCED SEARCH
  advancedQuery: string = '';
  advRanges: any = {
    reviewCount: { min: null, max: null },
    avgEquipmentGrade: { min: null, max: null },
    avgStaffGrade: { min: null, max: null },
    avgHygieneGrade: { min: null, max: null },
    avgSpaceGrade: { min: null, max: null },
  };

  numericFields = [
    { name: 'reviewCount', label: 'Reviews count' },
    { name: 'avgEquipmentGrade', label: 'Avg Equipment Grade' },
    { name: 'avgStaffGrade', label: 'Avg Staff Grade' },
    { name: 'avgHygieneGrade', label: 'Avg Hygiene Grade' },
    { name: 'avgSpaceGrade', label: 'Avg Space Grade' },
  ];

  results: FacilityIndex[] = [];
  isAsc: boolean = true;

  constructor(private http: HttpClient, private config: ConfigService) {}

  // ✅ SIMPLE SEARCH - ostaje netaknut
  onSimpleSearch() {
    const ranges: any = {};
    for (const key of Object.keys(this.simpleRanges)) {
      const min = this.simpleRanges[key].min;
      const max = this.simpleRanges[key].max;
      if (min != null || max != null) {
        ranges[key] = { min: min ?? null, max: max ?? null };
      }
    }

    const payload = {
      keywords: this.simpleQuery.split(' ').filter(k => k.trim() !== ''),
      expression: [],
      ranges,
      isAsc: this.isAsc,
    };

    this.http.post<any>(this.config.simple_search_url, payload, {
      headers: { 'Content-Type': 'application/json' },
    }).subscribe({
      next: (res) => {
        console.log('✅ Simple search response:', res);
        this.results = res.content;
      },
      error: (err) => console.error('❌ Simple search error:', err),
    });
  }

  // ✅ ADVANCED SEARCH - NOVA LOGIKA
  onAdvancedSearch() {
    const ranges: any = {};
    for (const key of Object.keys(this.advRanges)) {
      const min = this.advRanges[key].min;
      const max = this.advRanges[key].max;
      if (min != null || max != null) {
        ranges[key] = { min, max };
      }
    }

    const parts = this.advancedQuery.match(/(.+?)\s+(AND|OR|NOT)\s+(.+)/i);
    if (!parts) {
      console.error("Advanced query must be in format: field:value OPERATOR field:value");
      return;
    }

    const expression = [parts[1].trim(), parts[2].toUpperCase(), parts[3].trim()];

    const payload = {
      keywords: [],
      expression,
      ranges,
      isAsc: this.isAsc,
    };

    console.log('📤 Advanced search payload:', payload);

    this.http.post<any>(this.config.advanced_search_url, payload, {
      headers: { 'Content-Type': 'application/json' },
    }).subscribe({
      next: (res) => {
        console.log('✅ Advanced search response:', res);
        this.results = res.content;
      },
      error: (err) => console.error('❌ Advanced search error:', err),
    });
  }

  // OPTIONAL: MLT (More Like This)
 andJustLikeThat() {
  // Trim i proveri da li postoji unos
  const keyword = this.simpleQuery?.trim();
  if (!keyword) {
    console.error("⚠️ Please enter some text for MLT search");
    return;
  }

  const payload = {
    keywords: [keyword], // 👈 mora biti lista stringova
    expression: [],      // bek ne koristi za MLT
    ranges: {},          // nema filtera
    isAsc: this.isAsc,   // sortiranje
  };

  console.log("📤 Sending MLT payload:", payload);

  this.http.post<any>(this.config.mlt_search_url, payload, {
    headers: { 'Content-Type': 'application/json' },
  }).subscribe({
    next: (res) => {
      console.log("✅ MLT response:", res);
      this.results = res.content || res; // bek vraća Page<FacilityIndex>
    },
    error: (err) => {
      console.error("❌ MLT request failed", err);
    },
  });
}


  // ✅ DOWNLOAD PDF
  downloadPDF(serverFilename: string) {
    this.http
      .get(this.config.getFileGetter(serverFilename), { responseType: 'blob' })
      .subscribe((blob) => {
        const a = document.createElement('a');
        const objectUrl = URL.createObjectURL(blob);
        a.href = objectUrl;
        a.download = serverFilename;
        a.click();
        URL.revokeObjectURL(objectUrl);
      });
  }

 
}
