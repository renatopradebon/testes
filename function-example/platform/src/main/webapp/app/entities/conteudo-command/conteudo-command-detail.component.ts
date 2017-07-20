import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConteudoCommand } from './conteudo-command.model';
import { ConteudoCommandService } from './conteudo-command.service';

@Component({
    selector: 'jhi-conteudo-command-detail',
    templateUrl: './conteudo-command-detail.component.html'
})
export class ConteudoCommandDetailComponent implements OnInit, OnDestroy {

    conteudoCommand: ConteudoCommand;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private conteudoCommandService: ConteudoCommandService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['conteudoCommand']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConteudoCommands();
    }

    load(id) {
        this.conteudoCommandService.find(id).subscribe((conteudoCommand) => {
            this.conteudoCommand = conteudoCommand;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConteudoCommands() {
        this.eventSubscriber = this.eventManager.subscribe('conteudoCommandListModification', (response) => this.load(this.conteudoCommand.id));
    }
}
