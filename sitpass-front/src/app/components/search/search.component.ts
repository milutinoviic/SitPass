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

  
  activeTab: 'simple' | 'advanced' = 'simple';
  simpleKeywords = '';
  isAsc = true;

  ranges: { [key: string]: { min: number | null, max: number | null } } = {
    reviewCount: { min: null, max: null },
    avgEquipmentGrade: { min: null, max: null },
    avgStaffGrade: { min: null, max: null },
    avgHygieneGrade: { min: null, max: null },
    avgSpaceGrade: { min: null, max: null }
  };

  results: FacilityIndex[] = [];
  loading = false;
  error: string | null = null;

  constructor(private searchService: SearchService) {}

  onSimpleSearch(): void {
    const filteredRanges: any = {};

    // Samo dodaj one koji nisu null
    for (const key in this.ranges) {
      const { min, max } = this.ranges[key];
      if (min != null || max != null) {
        filteredRanges[key] = { min, max };
      }
    }

    const query: SearchQueryDTO = {
      keywords: this.simpleKeywords.split(' ').filter(k => k.trim() !== ''),
      expression: [],
      ranges: filteredRanges,
      isAsc: this.isAsc
    };

    this.loading = true;
    this.error = null;

    this.searchService.simpleSearch(query).subscribe({
      next: (res) => {
        this.results = res.content;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Greška pri pretrazi';
        this.loading = false;
      }
    });
  }
  
 

}
