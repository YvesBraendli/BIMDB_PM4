export class Notification {
	public message: string;
	public type: BannerType;
	public icon: BannerIcon;

	public constructor(message: string, type: BannerType, icon: BannerIcon) {
		this.message = message;
		this.type = type;
		this.icon = icon;
	}
}

export class SuccessNotification extends Notification {
	public constructor(message: string) {
		super(message, 'success', 'done');
	}
}

export class InfoNotification extends Notification {
	public constructor(message: string) {
		super(message, 'info', 'info');
	}
}

export class WarningNotification extends Notification {
	public constructor(message: string) {
		super(message, 'warning', 'warning');
	}
}

export class ErrorNotification extends Notification {
	public constructor(message: string) {
		super(message, 'error', 'error');
	}
}

export type BannerType = 'success' | 'info' | 'warning' | 'error';

export type BannerIcon = 'done' | 'info' | 'warning' | 'error';
