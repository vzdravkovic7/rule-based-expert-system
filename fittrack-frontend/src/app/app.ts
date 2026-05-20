import { Component, signal } from '@angular/core';
import { ProfilFormaComponent } from './components/profil-forma/profil-forma';
import { NutricijaComponent } from './components/nutricija/nutricija';
import { TreningLogComponent } from './components/trening-log/trening-log';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ProfilFormaComponent, NutricijaComponent, TreningLogComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  title = signal('fittrack-frontend');
  aktivniTab = signal<string>('profil');

  promeniTab(imeTaba: string) {
    this.aktivniTab.set(imeTaba);
  }
}