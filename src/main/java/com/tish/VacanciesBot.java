package com.tish;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class VacanciesBot extends TelegramLongPollingBot {

	public List<String> levels = List.of("Junior", "Middle", "Senior");
	public Map<String, List<String>> levelMap = new HashMap<>();

	{
		levelMap.put("Junior", List.of("1", "2"));
		levelMap.put("Middle", List.of("3", "4"));
		levelMap.put("Senior", List.of("5", "6"));
	}

	public VacanciesBot(/*String botToken*/) {
		//super(botToken);
		super("6947385103:AAFtpi7rVpsJ82jZcAnClcb3HOlIo1CWJ78");
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.getMessage() != null) {
				handleStartCommand(update);
			}
			if (update.getCallbackQuery() != null) {
				String callbackData = update.getCallbackQuery().getData();

				if (callbackData.startsWith("show") && callbackData.endsWith("Vacancies")) {

					switch (callbackData) {
						case "showJuniorVacancies":
							showLevelVacancies("Junior", update);
							break;
						case "showMiddleVacancies":
							showLevelVacancies("Middle", update);
							break;
						case "showSeniorVacancies":
							showLevelVacancies("Senior", update);
							break;
					}

				} else if (callbackData.startsWith("vacancyId=")) {
					String id = callbackData.split("=")[1];
					showVacancyDescription(id, update);
				}
				/*if ("showJuniorVacancies".equalsIgnoreCase(callbackData)) {
					showJuniorVacancies(update);
				} else if (callbackData.startsWith("vacancyId=")) {
					String id = callbackData.split("=")[1];
					showVacancyDescription(id, update);
				}*/

			}
		} catch (TelegramApiException e) {
			throw new RuntimeException("Can't send message to user!", e);
		}
	}

	private void showVacancyDescription(String id, Update update) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
		sendMessage.setText("Vacancy description for vacancy with id = " + id);
		execute(sendMessage);
	}

	private void showLevelVacancies(String level, Update update) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Please choose vacancy:");
		sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
		//sendMessage.setReplyMarkup(getJuniorVacanciesMenu());
		sendMessage.setReplyMarkup(getLevelVacanciesMenu(level));
		execute(sendMessage);
	}

	private ReplyKeyboard getLevelVacanciesMenu(String level) {
		List<InlineKeyboardButton> raw = new ArrayList<>();

		InlineKeyboardButton mateVacancy = new InlineKeyboardButton();
		mateVacancy.setText(level + " Java developer at MA");
		mateVacancy.setCallbackData("vacancyId=" + levelMap.get(level).get(0));
		raw.add(mateVacancy);

		InlineKeyboardButton googleVacancy = new InlineKeyboardButton();
		googleVacancy.setText(level + " Dev at Google");
		googleVacancy.setCallbackData("vacancyId=" + levelMap.get(level).get(1));
		raw.add(googleVacancy);

		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		keyboard.setKeyboard(List.of(raw));

		return keyboard;
	}

	private void handleStartCommand(Update update) throws TelegramApiException {
		String text = update.getMessage().getText();
		System.out.println("Received text is " + text);
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(update.getMessage().getChatId());
		sendMessage.setText("Welcome to vacancies bot! Please choose your title:");
		sendMessage.setReplyMarkup(getStartMenu());
		execute(sendMessage);
	}

	private ReplyKeyboard getStartMenu() {
		List<InlineKeyboardButton> raw = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			InlineKeyboardButton button = new InlineKeyboardButton();
			button.setText(levels.get(i));
			button.setCallbackData("show" + levels.get(i) + "Vacancies");
			raw.add(button);
		}

		/*InlineKeyboardButton junior = new InlineKeyboardButton();
		junior.setText("Junior");
		junior.setCallbackData("showJuniorVacancies");
		raw.add(junior);

		InlineKeyboardButton middle = new InlineKeyboardButton();
		middle.setText("Middle");
		middle.setCallbackData("showMiddleVacancies");
		raw.add(middle);

		InlineKeyboardButton senior = new InlineKeyboardButton();
		senior.setText("Senior");
		senior.setCallbackData("showSeniorVacancies");
		raw.add(senior);*/

		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		keyboard.setKeyboard(List.of(raw));

		return keyboard;
	}

	@Override
	public String getBotUsername() {
		return "stish vacancy bot";
	}
}
