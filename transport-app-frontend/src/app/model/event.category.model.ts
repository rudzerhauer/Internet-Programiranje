export class EventCategory {
    id: number | null;
    name: string | null;

    constructor(id?: number, name?: string) {
        this.id = id || null;
        this.name = name || null;
    }
}