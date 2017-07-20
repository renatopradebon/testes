import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Usuario } from './usuario.model';
import { UsuarioService } from './usuario.service';

@Component({
    selector: 'jhi-usuario-detail',
    templateUrl: './usuario-detail.component.html'
})
export class UsuarioDetailComponent implements OnInit, OnDestroy {

    usuario: Usuario;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private usuarioService: UsuarioService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['usuario']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUsuarioes();
    }

    load(id) {
        this.usuarioService.find(id).subscribe((usuario) => {
            this.usuario = usuario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUsuarioes() {
        this.eventSubscriber = this.eventManager.subscribe('usuarioListModification', (response) => this.load(this.usuario.id));
    }
}
