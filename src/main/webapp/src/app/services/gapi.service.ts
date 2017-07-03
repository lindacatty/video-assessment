import { Observable } from 'rxjs/Rx';
import { Response } from '@angular/http';
import { ANY_STATE } from '@angular/core/src/animation/animation_constants';
import { Injectable } from '@angular/core';

import { Video } from '../common/video';
import { Template } from '../common/template';

// Google's login API namespace
declare var gapi: { client: { videoAssessmentApi: any } };

@Injectable()
export class GapiService {
  private gapi_: { client: { videoAssessmentApi: any } };
  static QUERY_LIMIT: number = 10000;

  constructor() {
    this.gapi_ = gapi;
  }

  getUploadUrl(): Promise<string> {
    return new Promise((resolve, reject) => 
        this.gapi_.client.videoAssessmentApi.getUploadUrl()
            .execute((resp) => {
              if (resp.error) {
                reject(resp.error);
              } else if (resp.result) {
                resolve(<string> resp.result.url);
              }
            }));
  }

  loadMyVideos(limit: number = 1000): Promise<Video[]> {
    return new Promise((resolve,reject) => 
        this.gapi_.client.videoAssessmentApi.getMyVideos(limit)
            .execute((resp) => {
              if (resp.error) {
                reject(resp.error);
              } else if (resp.result) {
                resolve(<Video[]> resp.result.items);
              }
            }));
  }

  loadTemplate(): Promise<Template> {
    return new Promise((resolve, reject) => {
      this.gapi_.client.videoAssessmentApi.getTemplate()
          .execute((resp) => {
            if (resp.error) {
                reject(resp.error);
              } else if (resp.result) {
                resolve(<Template> resp.result);
              }
          })
    });
  }

  submitResponses(
      videoKey: string, 
      templateId: number, 
      comments: string[], 
      scores: number[]): Promise<Template> {
    return new Promise((resolve, reject) => {
      this.gapi_.client.videoAssessmentApi.createResponses({
          videoId: videoKey,
          templateId: templateId, 
          comments: comments,
          scores: scores
      }).execute((resp) => {
        if (resp.error) {
            reject(resp.error);
          } else if (resp.result) {
            resolve(<Template> resp.result);
          }
      })
    });
  }
}
