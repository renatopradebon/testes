import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConteudoCommand } from './conteudo-command.model';
import { ConteudoCommandService } from './conteudo-command.service';
@Injectable()
export class ConteudoCommandPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private conteudoCommandService: ConteudoCommandService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.conteudoCommandService.find(id).subscribe((conteudoCommand) => {
                this.conteudoCommandModalRef(component, conteudoCommand);
            });
        } else {
            return this.conteudoCommandModalRef(component, new ConteudoCommand());
        }
    }

    conteudoCommandModalRef(component: Component, conteudoCommand: ConteudoCommand): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.conteudoCommand = conteudoCommand;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
