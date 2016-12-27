import { Injectable } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/throw';

import { IOrgHALPage, IOrganization } from 'app/api/IOrganization';
import { ApiBase } from 'app/api/ApiBase';
import {IContactHALPage, IContact} from 'app/api/IContact';

@Injectable()
export class OrganizationService extends ApiBase {
  base: string = '/api/organisations';
  constructor(private http: Http) {
    super();
  }

  all(page: number = 1, pageSize?: number): Observable<IOrgHALPage> {
    let params = new URLSearchParams();
    params.set('page', page.toString());
    //params.set('pageSize', pageSize.toString());
    return this.http.get(this.base, { search: params })
      .map(items => items.json())
      .catch(this.handleError);
  }

  get(orgId: string): Observable<IOrganization> {
    return this.http.get(this.base + '/' + orgId)
      .map(item => item.json())
      .catch(this.handleError);
  }

  getContacts(id: string): Observable<IContact[]> {
    return this.http.get(this.base + '/' + id + '/contacts')
      .map(item => item.json())
      .catch(this.handleError);
  }

  save(org: IOrganization) {
    let call = (org.id) ? this.http.put(this.base, org) : this.http.post(this.base, org); // If exists, put - else post
    return call.map(item => item.json()).catch(this.handleError);
  }

  // --------------------------
  // External calls
  // --------------------------
  fetchRegistryOrgByName(filter: string) {
    let params = new URLSearchParams();
    params.set('page', '0');
    params.set('size', '100');
    params.set('$filter', `startswith(navn,'${filter}') and (organisasjonsform eq 'FYLK' or organisasjonsform eq 'KOMM')`);
    return this.http.get('//data.brreg.no/enhetsregisteret/enhet.json', { search: params })
      .map(items => items.json().data)
      .catch(this.handleError);
  }

  fetchRegistryOrgByNumber(orgId: number) {
    let params = new URLSearchParams();
    params.set('page', '0');
    params.set('size', '100');
    params.set('$filter', `organisasjonsnummer eq '${orgId}' and (organisasjonsform eq 'FYLK' or organisasjonsform eq 'KOMM')`);
    return this.http.get('//data.brreg.no/enhetsregisteret/enhet.json', { search: params })
      .map(items => items.json().data)
      .catch(this.handleError);
  }
}