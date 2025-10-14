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
  advancedExpressions = '';
  ranges: { [key: string]: { min: number, max: number } } = {};
  isAsc = true;

  results: FacilityIndex[] = [];
  loading = false;
  error: string | null = null;

  constructor(private searchService: SearchService) {}

  onSimpleSearch(): void {
    const query:  SearchQueryDTO = {
      keywords: this.simpleKeywords.split(' ').filter(k => k.trim() !== ''),
      expression: [],
      ranges: {},
      isAsc: this.isAsc
    };

    this.loading = true;
    this.searchService.simpleSearch(query).subscribe({
      next: (res) => {
        this.results = res.content;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Greška pri pretrazi';
        this.loading = false;
      }
    });
  }

  onAdvancedSearch(): void {
    const query: SearchQueryDTO = {
      expression: this.advancedExpressions.split(',').map(e => e.trim()).filter(e => e),
      ranges: this.ranges,
      isAsc: this.isAsc
    };

    this.loading = true;
    this.searchService.advancedSearch(query).subscribe({
      next: (res) => {
        this.results = res.content;
        this.loading = false;
      },
      error: () => {
        this.error = 'Greška pri pretrazi';
        this.loading = false;
      }
    });
  }
  
 

}
